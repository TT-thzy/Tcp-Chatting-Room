package com.qq.ui.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.qq.pojo.User;

public class SearchFrame extends JFrame {
    private List<User> users = null;

    private JPanel jp = new JPanel();

    private JScrollPane jsp = null;

    private JList<User> jlist = null;

//	private DefaultListModel<User> model=new DefaultListModel<User>();

    public SearchFrame(List<User> users) {
        this.users = users;
        init();
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//		this.setUndecorated(true);
        this.setSize(385, 300);
        this.setVisible(true);

        jlist = new JList<User>();
        if (users != null) {
            jlist.setListData(users.toArray(new User[users.size()]));
        }

        //点击事件
//        jlist.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
//                    User value = jlist.getSelectedValue();
//
//                }
//            }
//        });

        jlist.setLayoutOrientation(jlist.VERTICAL);
        jlist.setFixedCellHeight(50);//行高
        jlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jlist.setCellRenderer(new MyQQCellPanelRender());


        jsp = new JScrollPane(jlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        this.getContentPane().add(jsp);
    }

}
