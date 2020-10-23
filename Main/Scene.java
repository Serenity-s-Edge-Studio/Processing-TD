import Green.*;
import processing.core.*;
import java.awt.*;
import java.util.*;
import java.io.*;
public abstract class Scene extends World {
  public static Map<String, Map<Integer, Map<Integer, Font>>> fonts = new HashMap<String, Map<Integer, Map<Integer, Font>>>();
  public Green engine;
  public Scene(Green engine) {
    super();
    this.engine = engine;
    setBackgroundColor(173, 216, 230);
  }
  public Scene(Green engine, PImage image) {
    super(image);
    this.engine = engine;
    setBackgroundColor(173, 216, 230);
  }
  public static Font getFont(String name, int style, int size) {
    Map<Integer, Map<Integer, Font>> styleMap = fonts.get(name); //<>//
    if (styleMap != null) {
      Map<Integer, Font> sizeMap = styleMap.get(style);
      if (sizeMap != null) {
        Font result = sizeMap.get(size);
        if (result != null) {
          return result;
        } else {
          Font awtfont = null;
          InputStream is = Green.getInstance().getParent().createInput(name);
          try{
            awtfont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(style, size);
          }
          catch(Exception e) {
            System.out.println("Failed to load font " + name);
            return null;
          }
          return awtfont;
        }
      } else {
        styleMap.put(style, new HashMap<Integer, Font>());
        return getFont(name, style, size);
      }
    } else {
      fonts.put(name, new HashMap<Integer, Map<Integer, Font>>());
      return getFont(name, style, size);
    }
  }
}
