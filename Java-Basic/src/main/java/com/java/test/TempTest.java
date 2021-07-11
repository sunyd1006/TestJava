package com.java.test;

import org.junit.Test;
import com.aliyun.odps.*;

public class TempTest {

    @Test
    public void testImportPersonalPackage(){
        String str = "run delete -w ttt ccc qqq";
        ToolMain.parseCommand(str);
    }
}
