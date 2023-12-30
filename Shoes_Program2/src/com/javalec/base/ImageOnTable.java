package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_test_imageOnTable;
import com.javalec.function.Dto_test_imageOnTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class ImageOnTable extends JDialog {
	
	
	private final DefaultTableModel OuterTable = new DefaultTableModel();
	ArrayList<Dto_test_imageOnTable> beanList = null;



	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable innnerTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ImageOnTable dialog = new ImageOnTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ImageOnTable() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				tableInit();
				queryAction();
				
				
			}
		});
		setTitle("asdf");
		setBounds(100, 100, 444, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 20, 383, 424);
			contentPanel.add(scrollPane);
			{
				innnerTable = new JTable() {
					
					public Class getColumnClass(int column) { 				// <--****************
				        return (column == 0) ? Icon.class : Object.class; 	// <--****************
				      }
				};
				innnerTable.setRowHeight(150); 		// <--***************************************************
				innnerTable.setModel(OuterTable); 	// <--***************************************************
					
			
				
				
				
				innnerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(innnerTable);
			}
		}
	}

	
	//function
	
	private void tableInit() {
        int i = OuterTable.getRowCount();
        
        OuterTable.addColumn("Picture");
        OuterTable.addColumn("Name");
        OuterTable.setColumnCount(2);

        for(int j = 0 ; j < i ; j++){
            OuterTable.removeRow(0);
        }

        innnerTable.setAutoResizeMode(innnerTable.AUTO_RESIZE_OFF);
        

        int vColIndex = 0;
        TableColumn col = innnerTable.getColumnModel().getColumn(vColIndex);
        int width = 200;
        col.setPreferredWidth(width);

        vColIndex = 1;
        col = innnerTable.getColumnModel().getColumn(vColIndex);
        width = 50;
        col.setPreferredWidth(width);
		
		
	}
	private void queryAction() {
		Dao_test_imageOnTable dbAction = new Dao_test_imageOnTable();
		beanList = dbAction.allProductLsit();
		
		int listCount = beanList.size();
		
		for (int index = 0; index < listCount; index++) {
			ImageIcon icon = new ImageIcon("./"+beanList.get(index).getFake_filename());
			Object[] tempData = {icon, beanList.get(index).getName()};
			OuterTable.addRow(tempData);
		}

		
	}
		
	private void closingAction() {
		
		for(int index=0; index < beanList.size(); index++) {
			File file = new File("./" + beanList.get(index).getFake_filename());
			file.delete();
			
		}
		
	}
	
	
	
}// End
