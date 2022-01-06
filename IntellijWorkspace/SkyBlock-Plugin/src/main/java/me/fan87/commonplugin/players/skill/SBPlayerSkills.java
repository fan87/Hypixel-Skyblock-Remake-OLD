package me.fan87.commonplugin.players.skill;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.SneakyThrows;
import me.fan87.commonplugin.players.skill.impl.SkillCombat;
import me.fan87.commonplugin.players.skill.impl.SkillFarming;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SBPlayerSkills {


    public SkillFarming skillFarming = new SkillFarming();
    public SkillCombat skillCombat = new SkillCombat();

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
