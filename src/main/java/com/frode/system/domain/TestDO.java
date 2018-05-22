package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private  Long id;
    private String content;

}
