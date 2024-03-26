package global;

import shapetools.GOval;
import shapetools.GRectangle;
import shapetools.GShape;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangle()), // 스펠링 안틀릴 수 있음 
		eOval("oval", new GOval()),
		eLine("line", new GRectangle()), 
		ePolygon("polygon", new GRectangle());
	
		private String text;
		private String string;
		private GShape shapeTool;
		EShapeButtons(String text, GShape shapeTool){
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
}
