/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Config;


import org.hibernate.envers.RevisionListener;
import org.springframework.data.history.Revision;

/**
 *
 * @author salguero
 */
public class CustomRevisionListener implements RevisionListener {
     public void newRevision(Object revisionEntity) {final Revision revision = (Revision) revisionEntity;}
}
