/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmsoft.fra.deskclient.generic.service;

import java.io.IOException;

/**
 *
 * @author carlos
 */
public class IlegalImageExtesion extends IOException{

    public IlegalImageExtesion(String msg) {
         super(msg);
    }
    
}
