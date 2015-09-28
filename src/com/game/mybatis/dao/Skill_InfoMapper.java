package com.game.mybatis.dao;

import com.game.mybatis.model.Skill_Info;

public interface Skill_InfoMapper {
    int deleteById(Integer skillId);

    int insertSkill(Skill_Info skill);

    Skill_Info selectById(Integer skillId);

    int updateSkill(Skill_Info skill);
}