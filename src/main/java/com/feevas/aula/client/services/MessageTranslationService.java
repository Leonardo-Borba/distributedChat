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

import javax.xml.bind.DatatypeConverter;

public class MessageTranslationService {

    public static String translate(String msg){
        return new String(DatatypeConverter.parseBase64Binary(msg.toString()));
    }

    public static String[] splitMessage(String msg){
        String regex = "\\s+";
        return msg.split(regex);
    }
}
