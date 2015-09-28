package com.game.mybatis.dao;

import com.game.mybatis.model.Dialog_Info;

public interface Dialog_InfoMapper {
    int deleteById(Integer id);

    int insertDialog(Dialog_Info info);

    Dialog_Info selectById(Integer id);

    int updateDialog(Dialog_Info info);
}