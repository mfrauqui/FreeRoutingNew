/*
 *  Copyright (C) 2014  Alfons Wirtz  
 *   website www.freerouting.net
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License at <http://www.gnu.org/licenses/> 
 *   for more details.
 *
 * MainPopupMenu.java
 *
 * Created on 17. Februar 2005, 05:42
 */

package gui.menu;

import freert.main.Ldbg;
import freert.main.Mdbg;
import freert.main.Stat;
import gui.BoardFrame;

/**
 * Popup Menu used in the interactive select state.
 *
 * @author Alfons Wirtz
 */
public class PopupMenuMain extends PopupMenuDisplay
   {
   private static final long serialVersionUID = 1L;

   public PopupMenuMain(Stat stat, BoardFrame p_board_frame)
      {
      super(stat, p_board_frame);
      java.util.ResourceBundle resources = java.util.ResourceBundle.getBundle("gui.resources.PopupMenuMain", p_board_frame.get_locale());

      // add the item for selecting items

      javax.swing.JMenuItem select_item_item = new javax.swing.JMenuItem();
      select_item_item.setText(resources.getString("select_item"));
      select_item_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.select_items(board_panel.right_button_click_location);
               }
         });

      this.add(select_item_item, 0);

      // Insert the start route item.

      javax.swing.JMenuItem start_route_item = new javax.swing.JMenuItem();
      start_route_item.setText(resources.getString("start_route"));
      start_route_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.start_route(board_panel.right_button_click_location);
               }
         });

      this.add(start_route_item, 1);

      // Insert the create_obstacle_menu.

      javax.swing.JMenu create_obstacle_menu = new javax.swing.JMenu();

      create_obstacle_menu.setText(resources.getString("create_keepout"));

      javax.swing.JMenuItem create_tile_item = new javax.swing.JMenuItem();
      create_tile_item.setText(resources.getString("tile"));
      create_tile_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.start_tile(board_panel.right_button_click_location);
               }
         });

      if ( ! board_panel.debug(Mdbg.GUI_MENU, Ldbg.RELEASE))
         {
         // obstacle menu is created when gui is NOT a release
         create_obstacle_menu.add(create_tile_item);
         }

      javax.swing.JMenuItem create_circle_item = new javax.swing.JMenuItem();
      create_circle_item.setText(resources.getString("circle"));
      create_circle_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.start_circle(board_panel.right_button_click_location);
               }
         });

      create_obstacle_menu.add(create_circle_item);

      javax.swing.JMenuItem create_polygon_item = new javax.swing.JMenuItem();
      create_polygon_item.setText(resources.getString("polygon"));
      create_polygon_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.start_polygonshape_item(board_panel.right_button_click_location);
               }
         });

      create_obstacle_menu.add(create_polygon_item);

      javax.swing.JMenuItem add_hole_item = new javax.swing.JMenuItem();
      add_hole_item.setText(resources.getString("hole"));
      add_hole_item.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
               {
               board_panel.itera_board.start_adding_hole(board_panel.right_button_click_location);
               }
         });

      create_obstacle_menu.add(add_hole_item);

      this.add(create_obstacle_menu, 2);

      // Insert the pin swap item.

      if (board_panel.itera_board.get_routing_board().brd_library.logical_parts.count() > 0)
         {
         // the board contains swappable gates or pins
         javax.swing.JMenuItem swap_pin_item = new javax.swing.JMenuItem();
         swap_pin_item.setText(resources.getString("swap_pin"));
         swap_pin_item.addActionListener(new java.awt.event.ActionListener()
            {
               public void actionPerformed(java.awt.event.ActionEvent evt)
                  {
                  board_panel.itera_board.swap_pin(board_panel.right_button_click_location);
                  }
            });

         this.add(swap_pin_item, 3);
         }
      }
   }
