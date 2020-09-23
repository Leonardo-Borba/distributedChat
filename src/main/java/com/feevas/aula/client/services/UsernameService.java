/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.feevas.aula.client.services;

import com.feevas.aula.client.connection.SocketConnection;

import javax.swing.*;

public class UsernameService {


    public static void setUsername(JFrame frame, SocketConnection connection){
        boolean isNameSet = false;

        do {
            String name = showPanel(frame);
            if(!UserListService.getUserList().contains(name)){
                connection.setName(name);
                isNameSet = true;
            }

        } while (!isNameSet);


    }

    private static String showPanel(JFrame frame) {
        return JOptionPane.showInputDialog(frame,
                "What is your name?", null);
    }
}
