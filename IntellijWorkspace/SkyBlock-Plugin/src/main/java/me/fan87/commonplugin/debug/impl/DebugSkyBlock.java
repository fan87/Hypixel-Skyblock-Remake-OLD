package me.fan87.commonplugin.debug.impl;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.debug.DebugGui;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DebugSkyBlock extends DebugGui {



    public DebugSkyBlock(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected String getTitle() {
        return "SkyBlock Debug";
    }

    private final float[] memoryUsage = new float[256];
    private long lastMemoryUsage = 0;
    private Map<String, ImString> kickReasons = new HashMap<>();
    private Map<String, ImBoolean> showPlayerGui = new HashMap<>();

    private ImString searchPlayer = new ImString();

    private ImBoolean getShowGui(String uuid) {
        if (showPlayerGui.containsKey(uuid)) {
            return showPlayerGui.get(uuid);
        }
        return showPlayerGui.put(uuid, new ImBoolean());
    }

    @Override
    public void process() {
        try {
            performance: {
                ImGui.begin("Performance");
                if (System.currentTimeMillis() - lastMemoryUsage > 100) {
                    for (int i = 0; i < memoryUsage.length; i++) {
                        if (i != 0) {
                            memoryUsage[i - 1] = memoryUsage[i];
                        }
                    }
                    memoryUsage[memoryUsage.length - 1] = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory())/1024/1024;
                    lastMemoryUsage = System.currentTimeMillis();
                }
                ImGui.plotLines("Memory Usage", memoryUsage, 256, 0, String.format("%s/%sMB", memoryUsage[memoryUsage.length-1], Runtime.getRuntime().maxMemory()/1024/1024), 3000, 5000, 200f, 100f);
                ImGui.end();
            }

            players: {
                ImGui.begin("Players");
                ImGui.beginChild("Scrolling");
                ImGui.inputText("Search", searchPlayer);
                for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {
                    try {
                        String text = loadedPlayer.getPlayer().getName() + " (" + loadedPlayer.getPlayer().getUniqueId().toString() + ")";
                        if (searchPlayer.get().equals("")) {
                            ImGui.checkbox(text, getShowGui(loadedPlayer.getPlayer().getUniqueId().toString()));
                        } else {
                            if (text.contains(searchPlayer.get())) {
                                ImGui.checkbox(text, getShowGui(loadedPlayer.getPlayer().getUniqueId().toString()));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ImGui.endChild();
                ImGui.end();
            }

            playersTabs: {
                for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {
                    try {
                        if (getShowGui(loadedPlayer.getPlayer().getUniqueId().toString()).get()) {
                            ImGui.begin("Player: " + loadedPlayer.getPlayer().getName());
                            if (ImGui.collapsingHeader("Player Info")) {
                                ImString kickReason = new ImString();
                                if (!kickReasons.containsKey(loadedPlayer.getPlayer().getUniqueId().toString())) {
                                    kickReasons.put(loadedPlayer.getPlayer().getUniqueId().toString(), kickReason);
                                } else {
                                    kickReason = kickReasons.get(loadedPlayer.getPlayer().getUniqueId().toString());
                                }
                                ImGui.beginChild("Info: " + loadedPlayer.getPlayer().getName());
                                ImGui.text("Player Name: " + loadedPlayer.getPlayer().getName());
                                ImGui.text("UUID: " + loadedPlayer.getPlayer().getUniqueId());
                                float[] health = new float[] {
                                        ((float) loadedPlayer.getPlayer().getHealth())
                                };
                                float[] foodLevel = new float[] {
                                        ((float) loadedPlayer.getPlayer().getFoodLevel())
                                };
                                boolean updateHealth = false;
                                boolean updateFoodLevel = false;
                                boolean updateFlight = false;
                                ImBoolean allowedFlight = new ImBoolean(loadedPlayer.getPlayer().getAllowFlight());

                                if (!loadedPlayer.getPlayer().isDead()) {
                                    updateHealth = ImGui.sliderFloat("Health", health, 1, ((float) loadedPlayer.getPlayer().getMaxHealth()));
                                } else {
                                    ImGui.textColored(0xff0000ff, "Dead");
                                }
                                updateFoodLevel = ImGui.sliderFloat("Food", foodLevel, 0, 20f);
                                ImGui.text("Display Name: " + loadedPlayer.getPlayer().getDisplayName());
                                ImGui.text("On Ground: " + loadedPlayer.getPlayer().isOnGround());
                                ImGui.text("Fall Distance: " + loadedPlayer.getPlayer().getFallDistance());
                                ImGui.text("Walk Speed: " + loadedPlayer.getPlayer().getWalkSpeed());
                                ImGui.text("Fly Speed: " + loadedPlayer.getPlayer().getFlySpeed());
                                ImGui.text("Flying: " + loadedPlayer.getPlayer().isFlying());
                                updateFlight = ImGui.checkbox("Allowed Flight", allowedFlight);
                                ImGui.text(String.format("Position: %s / %s / %s", loadedPlayer.getPlayer().getLocation().getBlockX(), loadedPlayer.getPlayer().getLocation().getBlockY(), loadedPlayer.getPlayer().getLocation().getBlockZ()));
                                boolean finalUpdateHealth = updateHealth;
                                boolean finalUpdateFoodLevel = updateFoodLevel;
                                boolean finalUpdateFlight = updateFlight;
                                boolean kill = ImGui.button("Kill");
                                ImGui.inputText("Reason", kickReason);
                                boolean kick = ImGui.button("Kick");
                                boolean openGui = ImGui.button("Open SkyBlock Menu");
                                boolean closeGui = ImGui.button("Close Gui");
                                boolean clearInventory = ImGui.button("Clear Inventory");
                                ImString finalKickReason = kickReason;
                                skyBlock.getServer().getScheduler().runTask(skyBlock, new Runnable() {
                                    @Override
                                    public void run() {
                                        if (finalUpdateHealth) loadedPlayer.getPlayer().setHealth(health[0]);
                                        if (finalUpdateFoodLevel) loadedPlayer.getPlayer().setFoodLevel((int) foodLevel[0]);
                                        if (finalUpdateFlight) loadedPlayer.getPlayer().setAllowFlight(allowedFlight.get());
                                        if (kill) loadedPlayer.getPlayer().setHealth(0);
                                        if (kick) loadedPlayer.getPlayer().kickPlayer(finalKickReason.get());
                                        if (openGui) loadedPlayer.openSkyBlockMenu();
                                        if (closeGui) loadedPlayer.getPlayer().closeInventory();
                                        if (clearInventory) loadedPlayer.getPlayer().getInventory().clear();
                                    }
                                });

                                ImGui.endChild();
                            }
                            if (ImGui.collapsingHeader("Player Stats")) {
                                SBPlayerStats stats = loadedPlayer.getStats();
                                for (Field declaredField : stats.getClass().getDeclaredFields()) {
                                    try {
                                        declaredField.setAccessible(true);
                                        if (declaredField.get(stats) instanceof SBStat) {
                                            SBStat stat = ((SBStat) declaredField.get(stats));
                                            ImGui.setCursorPosX(20f);
                                            if (ImGui.collapsingHeader(stat.getName())) {
                                                ImGui.beginChild(stat.getName(), ImGui.getWindowWidth() - 20, 50);
                                                ImGui.setCursorPosX(20f);
                                                float[] baseValue = new float[] {
                                                        (float) stat.getBaseValue()
                                                };
                                                if (ImGui.sliderFloat("Base Value", baseValue, 0, 1000)) {
//                                                    stat.setBaseValue(baseValue[0]);
                                                }
                                                for (SBStat.StatBonus statBonus : stat.getBonusValue()) {
                                                    double value = statBonus.getValue();
                                                    ImGui.text((value<0?"":"+") + value + " ( Time Left: " + (statBonus.getExpirationTime() - System.currentTimeMillis()) + ")");
                                                }
                                                ImGui.setCursorPosX(0f);
                                                ImGui.endChild();
                                            }
                                            ImGui.setCursorPosX(0f);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (ImGui.collapsingHeader("Switches")) {
                                ImBoolean showActionBar = new ImBoolean(loadedPlayer.showActionBar);
                                if (ImGui.checkbox("Show Action Bar", showActionBar)) {
                                    loadedPlayer.showActionBar = showActionBar.get();
                                }
                            }
                            ImGui.end();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            server: {
                ImGui.begin("Server Management");
                ImGui.text("Worlds Loaded: " + skyBlock.getServer().getWorlds().size());
                ImGui.pushStyleColor(ImGuiCol.Button, 0.8f, 0f, 0f, 0.8f);
                if (ImGui.button("Stop Server")) {
                    skyBlock.getServer().shutdown();
                }
                ImGui.popStyleColor();
                ImGui.end();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
