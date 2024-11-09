/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cinemamanager;

import Classes.*;
import Controllers.LoginController;
import Views.LoginView;
import Views.ManagerView;
import java.awt.EventQueue;


/**
 *
 * @author Lenovo
 */

// lớp chứa hàm main thực thi chương trình
public class CinemaManager {

     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}
