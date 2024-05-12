package global;

import shapetools.GLine;
import shapetools.GOval;
import shapetools.GPolygon;
import shapetools.GRectangle;
import shapetools.GShape;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangle()), // 스펠링 안틀릴 수 있음 
		eOval("oval", new GOval()),
		eLine("line", new GLine()), 
		ePolygon("polygon", new GPolygon());
	
		private String text;
		private GShape shapeTool;
		private EShapeButtons(String text, GShape shapeTool){
			this.text = text;
			this.shapeTool = shapeTool;
		}
		
		public String getText() {
			return text;
		}
		public GShape getShapeTool () {
			return this.shapeTool;
		}
	}
	
	public final static int NUM_POINTS = 20;
}
