import java.awt.Color;

/* 
 * This class creates an empty grid when called containing only white cells.
 * It takes x and y vales to make width and height of the grid
 * It also takes integer number for magnification.
 * 
 * */

public class MakeGrid {
	private int x,y;            // x-by-y grid of cells
    private int magnification;  // pixel-width of each cell
    public int[][] cells;      // cells to be randomly colored
    private Picture pic;        // picture to be drawn on screen
    
    // This method creates an x*y grid and calls function to create all white cells. 
    MakeGrid(int x, int y, int magnification){
    	this.x = x;
        this.y = y;
        this.magnification = magnification;
        cells = new int[x][y];
        pic = new Picture(x * magnification, y * magnification);
        
        
        for (int i = 0; i < x; i++)
        {
        	for (int j = 0; j < y; j++)
        	{
                this.drawCell(i,j);
            }
        }
    }
    
    // This method sets the color of the grid to black(1) or white(0) based on the value in the list.
    // It takes the list and change the color of the grid base on that.
    public void setColor(int list[][]) {
    	
    	for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) 
			{ if (list[i][j] == 0) {
				Color col = new Color(255,255,255); // Setting white color
				for (int offsetX = 0; offsetX < magnification; offsetX++)
				{
					for (int offsetY = 0; offsetY < magnification; offsetY++)
			            {
						// set() colors an individual pixel
						pic.set((i*magnification)+offsetX,(j*magnification)+offsetY, col);
			            }
			        }
				}
				else {
					Color col = new Color(0,0,0); // Setting black color
					for (int offsetX = 0; offsetX < magnification; offsetX++)
			        {
			            for (int offsetY = 0; offsetY < magnification; offsetY++)
			            {// set() colors an individual pixel
			             pic.set((i*magnification)+offsetX,(j*magnification)+offsetY, col);
			             }
			            }
					}
				}
			}
    	}
    
//  fill a cell with a white color
    private void drawCell(int i, int j)
    {
        Color col = new Color(255,255,255); // Setting white color
        
        for (int offsetX = 0; offsetX < magnification; offsetX++)
        {
            for (int offsetY = 0; offsetY < magnification; offsetY++)
            {
                // set() colors an individual pixel
                pic.set((i*magnification)+offsetX,(j*magnification)+offsetY, col);
            }
        }
    }
    
    // display (or update) the picture on screen
    public void show()
    {
        pic.show();     // Showing the grid in graphical form.
    }

}
