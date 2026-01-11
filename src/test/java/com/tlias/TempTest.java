package com.tlias;

import org.junit.jupiter.api.Test;

public class TempTest {
    @Test
    void contextLoads() {
        String name="012.456.89";
        String substring = name.substring(name.lastIndexOf("."));
        System.out.println(substring);

        String substring1 = name.substring(1, 7);
        System.out.println(substring1);
    }
}
