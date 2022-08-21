package com.qq.ui.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;


/**
 * 重写setui中的方法 自定义tabbed组件样式
 * */
public class MyTabed extends BasicTabbedPaneUI{
	@Override//自定义高
	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight)+10;
	}

	@Override//自定义宽
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics)+136;
	}
	
	@Override//绘制选中获得焦点的框体
	protected void paintFocusIndicator(Graphics g, int tabPlacement,
			Rectangle[] rects, int tabIndex, Rectangle iconRect,
			Rectangle textRect, boolean isSelected) {
		 Rectangle tabRect = rects[tabIndex];
		if(tabPane.hasFocus() && isSelected){
			 int x, y, w, h;
	            g.setColor(Color.white);
	            switch(tabPlacement) {
	              case LEFT:
	                  x = tabRect.x + 3;
	                  y = tabRect.y + 3;
	                  w = tabRect.width - 5;
	                  h = tabRect.height - 6;
	                  break;
	              case RIGHT:
	                  x = tabRect.x + 2;
	                  y = tabRect.y + 3;
	                  w = tabRect.width - 5;
	                  h = tabRect.height - 6;
	                  break;
	              case BOTTOM:
	                  x = tabRect.x + 3;
	                  y = tabRect.y + 2;
	                  w = tabRect.width - 6;
	                  h = tabRect.height - 5;
	                  break;
	              case TOP:
	              default:
	                  x = tabRect.x + 3;
	                  y = tabRect.y + 3;
	                  w = tabRect.width - 6;
	                  h = tabRect.height - 5;
	            }
//	            BasicGraphicsUtils.drawDashedRect(g, x, y, w, h);
		
		}
	}
	
	@Override//设置背景色
    protected void paintTabBackground(Graphics g, int tabPlacement,
            int tabIndex,
            int x, int y, int w, int h,
            boolean isSelected ) {
    	Color selectedColor = UIManager.getColor("TabbedPane.selected");
    	g.setColor(!isSelected || selectedColor == null?
    			tabPane.getBackgroundAt(tabIndex) :Color.white );
    			switch(tabPlacement) {
    			case LEFT:
    			g.fillRect(x+1, y+1, w-1, h-3);		
    			break;
    			case RIGHT:
    			g.fillRect(x, y+1, w-2, h-3);
    			break;
    			case BOTTOM:
    			g.fillRect(x+1, y, w-3, h-1);
    			break;
    			case TOP:
    			default:
    			g.fillRect(x+1, y+1, w-3, h-1);
    			}
    			
    }
	
	
	
	protected void paintTabBorder(Graphics g, int tabPlacement,
            int tabIndex,
            int x, int y, int w, int h,
            boolean isSelected ) {
		g.setColor(Color.white);

		switch (tabPlacement) {
		case LEFT:
			g.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight
			g.drawLine(x, y+2, x, y+h-3); // left highlight
			g.drawLine(x+1, y+1, x+1, y+1); // top-left highlight
			g.drawLine(x+2, y, x+w-1, y); // top highlight

			g.setColor(Color.white);
			g.drawLine(x+2, y+h-2, x+w-1, y+h-2); // bottom shadow

			g.setColor(Color.white);
			g.drawLine(x+2, y+h-1, x+w-1, y+h-1); // bottom dark shadow
			break;
		case RIGHT:
			g.drawLine(x, y, x+w-3, y); // top highlight

			g.setColor(Color.white);
			g.drawLine(x, y+h-2, x+w-3, y+h-2); // bottom shadow
			g.drawLine(x+w-2, y+2, x+w-2, y+h-3); // right shadow

            g.setColor(Color.white);
            g.drawLine(x+w-2, y+1, x+w-2, y+1); // top-right dark shadow
            g.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
            g.drawLine(x+w-1, y+2, x+w-1, y+h-3); // right dark shadow
            g.drawLine(x, y+h-1, x+w-3, y+h-1); // bottom dark shadow
            break;
        case BOTTOM:
            g.drawLine(x, y, x, y+h-3); // left highlight
            g.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight

            g.setColor(Color.white);
            g.drawLine(x+2, y+h-2, x+w-3, y+h-2); // bottom shadow
            g.drawLine(x+w-2, y, x+w-2, y+h-3); // right shadow

            g.setColor(Color.white);
            g.drawLine(x+2, y+h-1, x+w-3, y+h-1); // bottom dark shadow
            g.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
            g.drawLine(x+w-1, y, x+w-1, y+h-3); // right dark shadow
            break;
        case TOP:
        default:
            g.drawLine(x, y+2, x, y+h-1); // left highlight
            g.drawLine(x+1, y+1, x+1, y+1); // top-left highlight
            g.drawLine(x+2, y, x+w-3, y); // top highlight

            g.setColor(Color.white);
            g.drawLine(x+w-2, y+2, x+w-2, y+h-1); // right shadow

            g.setColor(Color.white);
            g.drawLine(x+w-1, y+2, x+w-1, y+h-1); // right dark-shadow
            g.drawLine(x+w-2, y+1, x+w-2, y+1); // top-right shadow
      }
  }
	
	
	 protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
             int selectedIndex,
             int x, int y, int w, int h) {
		 Rectangle selRect = selectedIndex < 0? null :
			 getTabBounds(selectedIndex, calcRect);

		 g.setColor(Color.white);

// Draw unbroken line if tabs are not on RIGHT, OR
// selected tab is not in run adjacent to content, OR
// selected tab is not visible (SCROLL_TAB_LAYOUT)
//
		 if (tabPlacement != RIGHT || selectedIndex < 0 ||
				 (selRect.x - 1 > w) ||
				 (selRect.y < y || selRect.y > y + h)) {
			 g.drawLine(x+w-2, y+1, x+w-2, y+h-3);
			 g.setColor(Color.white);
			 g.drawLine(x+w-1, y, x+w-1, y+h-1);
		 } else {
			 // Break line to show visual connection to selected tab
			 g.drawLine(x+w-2, y+1, x+w-2, selRect.y - 1);
			 g.setColor(Color.white);
			 g.drawLine(x+w-1, y, x+w-1, selRect.y - 1);

			 if (selRect.y + selRect.height < y + h - 2) {
				 g.setColor(Color.white);
				 g.drawLine(x+w-2, selRect.y + selRect.height,
						 x+w-2, y+h-2);
				 g.setColor(Color.white);
				 g.drawLine(x+w-1, selRect.y + selRect.height,
						 x+w-1, y+h-2);
			 }
		 }
	 }
	 
	 protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
		 	boolean contentOpaque = true;
		 	Color selectedColor = UIManager.getColor("TabbedPane.selected");
		    boolean tabsOverlapBorder= UIManager.getBoolean("TabbedPane.tabsOverlapBorder");
	        int width = tabPane.getWidth();
	        int height = tabPane.getHeight();
	        Insets insets = tabPane.getInsets();
	        Insets tabAreaInsets = getTabAreaInsets(tabPlacement);

	        int x = insets.left;
	        int y = insets.top;
	        int w = width - insets.right - insets.left;
	        int h = height - insets.top - insets.bottom;

	        switch(tabPlacement) {
	          case LEFT:
	              x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
	              if (tabsOverlapBorder) {
	                  x -= tabAreaInsets.right;
	              }
	              w -= (x - insets.left);
	              break;
	          case RIGHT:
	              w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
	              if (tabsOverlapBorder) {
	                  w += tabAreaInsets.left;
	              }
	              break;
	          case BOTTOM:
	              h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
	              if (tabsOverlapBorder) {
	                  h += tabAreaInsets.top;
	              }
	              break;
	          case TOP:
	          default:
	              y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
	              if (tabsOverlapBorder) {
	                  y -= tabAreaInsets.bottom;
	              }
	              h -= (y - insets.top);
	        }

	            if ( tabPane.getTabCount() > 0 && (contentOpaque || tabPane.isOpaque()) ) {
	            // Fill region behind content area
	            Color color = UIManager.getColor("TabbedPane.contentAreaColor");
	            if (color != null) {
	                g.setColor(Color.white);
	            }
	            else if ( selectedColor == null || selectedIndex == -1 ) {
	                g.setColor(Color.white);
	            }
	            else {
	                g.setColor(Color.white);
	            }
	            g.fillRect(x,y,w,h);
	        }

	        paintContentBorderTopEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	        paintContentBorderLeftEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	        paintContentBorderBottomEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	        paintContentBorderRightEdge(g, tabPlacement, selectedIndex, x, y, w, h);

	    }



	
	

}
