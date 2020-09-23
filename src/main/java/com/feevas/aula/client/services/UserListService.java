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

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserListService {

    private static ArrayList<String> userList = new ArrayList<>();
    private static JList userListComponent;


    public static void updateUserList(String msg) {
        String[] split = MessageTranslationService.splitMessage(msg);

        String stringList = MessageTranslationService.translate(split[split.length - 1]);
        userList.clear();
        userList.addAll(Arrays.asList(MessageTranslationService.splitMessage(stringList)));
        setUsersInComponent();
    }

    private static void setUsersInComponent() {
        if(userListComponent != null){
            DefaultListModel<String> listModel = new DefaultListModel<>();
            userListComponent.setModel(listModel);
            userList.forEach(listModel::addElement);
        }
    }

    public static ArrayList<String> getUserList() {
        return userList;
    }

    public static void setUserListComponent(JList userList) {
        userListComponent = userList;
    }
}
