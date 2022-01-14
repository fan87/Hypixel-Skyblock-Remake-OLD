package me.fan87.commonplugin.commands.impl;

import lombok.SneakyThrows;
import me.fan87.commonplugin.areas.AreasManager;
import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class CmdParsePerformanceData extends SBCommand {
    public CmdParsePerformanceData() {
        super("parseperformance", "Parses the performance data.", "", "/parseperformance", "pp");
    }

    @Override
    @SneakyThrows
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(ChatColor.RED + "Only console can execute this command!");
            return true;
        }
        File out = new File(System.currentTimeMillis() + "-PP.txt");
        if (!out.exists()) out.createNewFile();
        Map<String, List<Long>> pp = new HashMap<>(EventManager.pp);
        Map<String, List<Long>> eventProcessTime = new HashMap<>(EventManager.eventProcessTime);
        Map<String, Long> eventsTime = new HashMap<>(EventManager.postedTimes);
        new Thread(() -> {
            try {
                PrintWriter writer = new PrintWriter(out);
                sender.sendMessage(ChatColor.GRAY + "Saving data...");
                List<String> keys = new ArrayList<>(pp.keySet());
                keys.sort(Comparator.comparingDouble(o -> {
                    long sum = 0;
                    for (Long aLong : pp.get(o)) {
                        sum += aLong;
                    }
                    return -1 * (sum*1d / pp.get(o).size());
                }));
                writer.println("Handler Process Time (Ordered by avg):");
                double totalAvg = 0;
                for (String s : keys) {
                    long sum = 0;
                    for (Long aLong : pp.get(s)) {
                        sum += aLong;
                    }
                    String x = s + " ->  Total Time Took: " + sum + "    Avg Time: " + sum*1d / pp.get(s).size();
                    totalAvg += sum*1d / pp.get(s).size();
                    writer.println("  " + x);
                }
                writer.println("Total Avg Time: " + totalAvg + " (Should be below 50 for 20 TPS)");
                writer.println("");
                writer.println("Events Posted (Ordered by times):");
                List<String> etKeys = new ArrayList<>(eventsTime.keySet());
                etKeys.sort(Comparator.comparingLong(e -> eventsTime.get(e)*-1));
                for (String etKey : etKeys) {
                    writer.println("  " + etKey + " -> Fired: " + eventsTime.get(etKey));
                }
                writer.println("");
                keys = new ArrayList<>(eventProcessTime.keySet());
                keys.sort(Comparator.comparingDouble(o -> {
                    long sum = 0;
                    for (Long aLong : eventProcessTime.get(o)) {
                        sum += aLong;
                    }
                    return -1 * (sum*1d / eventProcessTime.get(o).size());
                }));
                double eventProcessAvgSum = 0;
                writer.println("Event Process Time (Ordered by avg):");
                for (String s : keys) {
                    long sum = 0;
                    for (Long aLong : eventProcessTime.get(s)) {
                        sum += aLong;
                    }
                    double v = sum * 1d / eventProcessTime.get(s).size();
                    eventProcessAvgSum += v;
                    String x = s + " ->  Total Time Took: " + sum + "    Avg Time: " + v;
                    writer.println("  " + x);
                }
                writer.println("Total Avg Time: " + eventProcessAvgSum + " (Should be below 50 for 20 TPS)");
                writer.println("");
                List<Long> areaGettingPerformance = AreasManager.areaGettingPerformance;
                {
                    double sum = 0;
                    double max = 0;
                    double min = 0;
                    for (Long aLong : areaGettingPerformance) {
                        if (aLong < min) min = aLong;
                        if (aLong > max) max = aLong;
                        sum += aLong;
                    }
                    writer.println("AreaManager.getAreaOf()    Avg Time: " + sum/areaGettingPerformance.size() + "  Max: " + max + "  Min: " + min);
                }
                writer.close();
                sender.sendMessage(ChatColor.GREEN + "Performance data has been saved to " + out.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return true;
    }


}
