package me.fan87.commonplugin.players.skill;

import lombok.SneakyThrows;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.skill.impl.SkillCombat;
import me.fan87.commonplugin.players.skill.impl.SkillFarming;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SBPlayerSkills {

    public SBPlayerSkills(SBPlayer player) {
        skillCombat = new SkillCombat(player, 69690);
        skillFarming = new SkillFarming(player, 0);
    }

    public SkillCombat skillCombat;
    public SkillFarming skillFarming;

    @SneakyThrows
    public SBSkill[] getSkills() {
        List<SBSkill> out = new ArrayList<>();
        for (Field declaredField : getClass().getDeclaredFields()) {
            if (declaredField.getType().getSuperclass() == SBSkill.class) {
                out.add((SBSkill) declaredField.get(this));
            }
        }
        return out.toArray(new SBSkill[0]);
    }

}
