package me.fan87.commonplugin.npc.impl.deepcaverns;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.impl.deepcavrens.GuiLiftOperator;
import me.fan87.commonplugin.npc.NPCManager;
import me.fan87.commonplugin.npc.NPCPlayer;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.RotationUtils;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@NPCPlayer.NPCRegistry(
        addonName = "default",
        name = "Lift Operator",
        namespace = "LIFT_OPERATOR",
        skin = "eyJ0aW1lc3RhbXAiOjE1NTQ1NzE2OTkxOTAsInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg2NTJhMDZmN2I5OWU0ODIyYjQ5NWJhOWI2MDg5ZDRkNTE1MWU4M2JlZDk1NmE3Y2NjYjg0N2VhNDZhMjU3MjUifX19",
        world = WorldsManager.WorldType.DEEP_CAVERNS
)

public class NPCLiftOperator extends NPCPlayer {
    @Override
    public List<String> getLore(Player player) {
        return Arrays.asList(
                ChatColor.AQUA.toString() + "Lift Operator",
                ChatColor.YELLOW + ChatColor.BOLD.toString() + "CLICK"
        );
    }

    @Override
    protected void onInteract(SBPlayer player) {
        new GuiLiftOperator(player).open(player.getPlayer());
    }

    @Override
    protected void onTick() {
        Entity nearest = null;
        double lastDist = 0;
        for (Entity nearbyEntity : asCraftCopy().getWorld().getNearbyEntities(asCraftCopy().getLocation(), 5, 5, 5)) {
            if (nearest == null) {
                nearest = nearbyEntity;
                double deltaX = nearest.getLocation().getX() - asCraftCopy().getLocation().getX();
                double deltaY = nearest.getLocation().getY() - asCraftCopy().getLocation().getY();
                double deltaZ = nearest.getLocation().getZ() - asCraftCopy().getLocation().getZ();
                lastDist = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
                continue;
            }
            double deltaX = nearest.getLocation().getX() - asCraftCopy().getLocation().getX();
            double deltaY = nearest.getLocation().getY() - asCraftCopy().getLocation().getY();
            double deltaZ = nearest.getLocation().getZ() - asCraftCopy().getLocation().getZ();
            double sqrt = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (sqrt < lastDist) {
                nearest = nearbyEntity;
                lastDist = sqrt;
            }
        }
        if (nearest != null) {
            Location location = nearest.getLocation();
            float[] face = RotationUtils.tryFace(getNpcEntity().locX - location.getX(), getNpcEntity().locY - location.getY(), getNpcEntity().locZ - location.getZ());
            rotate(face[0], face[1]);
        }
    }

    public NPCLiftOperator(SkyBlock skyBlock) {
        super(skyBlock);
        setSkin("eyJ0aW1lc3RhbXAiOjE1NTQ1NzE2OTkxOTAsInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg2NTJhMDZmN2I5OWU0ODIyYjQ5NWJhOWI2MDg5ZDRkNTE1MWU4M2JlZDk1NmE3Y2NjYjg0N2VhNDZhMjU3MjUifX19", "W8e6ip++dGgqglbYiPyLxCEHUXpOMioWrf6bCJwMNPHmEFTJux+M0H4GZaDjs1+d7FcSHjg6rEbMWS1sZAL4TUh7Q9vCtEYlx0PfnkY42l1Qqo/tIpJvgY5J6RHZ1j1cvtfrgXfKU8AKpjZVDNNyAqc5iJWcgqAm1gj3SPH6SoVvfzZgx9avAKo3z440CRsIhLYwgwEtq8/sbkqi0y6cuwlCZ+reo7yy2Ohe5AwG0Sx7Tkkv0DIVC4wO2RYoP+4xw2MYi1SRWk9yv3ZKqjwhTP8ugB/xqK/R480vVrr7MLhCpLrbUzpuLiaAbfruF9/TmvBV2hXFSCrlqypo4EmLk1E3WSuJX5ls11+juht0M12MIUlmmYcsFjgnAux5tgJ+fQq3KWhrbYedEY4CG7swavIG71/ZZI/ugRCXz/KONOq7bXKn939CyIpPdfBBAo2RZhjk2QXG/bWODeTfJ6z5VlIivNzo65FCAdwJ54VQzmUcP86ID3/jrWQ5fE5fhPkGhpYtOlVn5S4xKdFtu6RfYYUX6cEPD6MRPcOzXB4ZvCzgD7QhKxIQDBc1S9XY35c6+ZDYHPokQa87iD053Yfft4PH/pZA21ovOcf7+Xa+AFu72wnsjbynkaZUqSrFz3mCdOl+TFynZe89SwgDX0t1mIPtGtJmtK5jbZpwFA4bqDE=");
    }
}
