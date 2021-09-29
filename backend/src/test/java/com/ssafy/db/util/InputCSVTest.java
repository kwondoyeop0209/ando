package com.ssafy.db.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class InputCSVTest {

    @Autowired
    InputCSV inputCSV;


    void inputCCTV() throws IOException {
        inputCSV.inputCCTV();
    }


    void inputBoan() throws IOException{
        inputCSV.inputLight();
    }
}