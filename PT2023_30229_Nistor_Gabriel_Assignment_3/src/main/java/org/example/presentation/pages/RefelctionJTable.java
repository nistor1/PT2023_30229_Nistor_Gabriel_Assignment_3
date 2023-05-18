package org.example.presentation.pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class RefelctionJTable {
    private  JTable table;

    public RefelctionJTable() {
    }

    public <T> JTable createJTableFromList(List<T> objectList) {
        DefaultTableModel tableModel = new DefaultTableModel();

        try {
            // Obține clasa obiectului generic
            Class<?> objectClass = objectList.get(0).getClass();

            // Obține toate câmpurile clasei obiectului generic
            Field[] fields = objectClass.getDeclaredFields();

            // Adaugă numele câmpurilor ca și coloane în modelul tabelului
            for (Field field : fields) {
                tableModel.addColumn(field.getName());
            }

            // Adaugă rândurile în modelul tabelului
            for (T object : objectList) {
                Object[] rowData = new Object[fields.length];

                // Obține valorile atributelor din obiectul curent
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    rowData[i] = fields[i].get(object);
                }

                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JTable(tableModel);
    }
}
