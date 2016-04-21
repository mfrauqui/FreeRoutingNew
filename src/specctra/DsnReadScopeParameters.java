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
 * ReadScopeParameter.java
 *
 * Created on 21. Juni 2004, 08:28
 */

package specctra;

import java.util.Collection;
import java.util.LinkedList;
import specctra.varie.DsnWriteResolution;
import autoroute.ArtSettings;
import board.varie.IdGenerator;
import board.varie.TraceAngleRestriction;
import datastructures.UnitMeasure;

/**
 * Default parameter type used while reading a Specctra dsn-file.
 * @author alfons
 */
public class DsnReadScopeParameters
   {
   public final JflexScanner scanner;
   final interactive.IteraBoard board_handling;
   final DsnNetList netlist = new DsnNetList();

   final board.BrdObservers observers;
   final IdGenerator item_id_no_generator;
   final board.varie.TestLevel test_level;

   DsnReadScopeParameters(JflexScanner p_scanner, interactive.IteraBoard p_board_handling, board.BrdObservers p_observers, IdGenerator p_item_id_no_generator,
         board.varie.TestLevel p_test_level)
      {
      scanner = p_scanner;
      board_handling = p_board_handling;
      observers = p_observers;
      item_id_no_generator = p_item_id_no_generator;
      test_level = p_test_level;
      }

   // The plane cannot be inserted directly into the boards, because the layers may not be read completely.
   final Collection<DsnPlaneInfo> plane_list = new LinkedList<DsnPlaneInfo>();
   final Collection<String[]> constants = new LinkedList<String[]>();

   /**
    * Component placement information. It is filled while reading the placement scope and can be evaluated after reading the library
    * and network scope.
    */
   public final Collection<DsnComponentPlacement> placement_list = new LinkedList<DsnComponentPlacement>();

   // The names of the via padstacks filled while reading the structure scope and evaluated after reading the library scope.
   Collection<String> via_padstack_names = null;

   boolean via_at_smd_allowed = false;        // damiano: should really pick up this from kicad proper
   TraceAngleRestriction snap_angle = null;

   // The logical parts are used for pin and gate swaw 
   Collection<DsnLogicalPartMapping> logical_part_mappings = new java.util.LinkedList<DsnLogicalPartMapping>();
   Collection<DsnLogicalPart> logical_parts = new java.util.LinkedList<DsnLogicalPart>();

   // The following objects are from the parser scope
   String string_quote = "\"";
   String host_cad = null;
   String host_version = null;

   boolean dsn_file_generated_by_host = true;

   boolean board_outline_ok = true;

   DsnWriteResolution write_resolution = null;

   // The following objects will be initialized when the structure scope is read
   DsnCoordinateTransform coordinate_transform = null;
   DsnLayerStructure layer_structure = null;
   ArtSettings autoroute_settings = null;

   // From this I should try to apply the same to the actual board, Damiano
   UnitMeasure wish_unit_meas = UnitMeasure.UM;
   int wish_resolution = 100; // default resolution is to divide each unit in 100 parts
   }