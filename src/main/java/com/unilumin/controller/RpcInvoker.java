package com.unilumin.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Scanner;

/**
 * @author zxz
 * @date 2023年08月04日 11:38
 */
public class RpcInvoker {
    public static String getUserInfo(){
        System.out.println("!!!!!!!!!!!!");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        return "done";
    }
}
