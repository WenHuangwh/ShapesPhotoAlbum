# ShapesPhotoAlbum
This is a Model-View-Controller architecture project. 

1.Program Design:
	a. PhotoAlbumMain class calls Controller class and passes String[] args in Controller.go() as parameter;
	b. Controller read args and open input txt file;
	c. Controller read txt file line by line and calls corresponding method in AlbumModel class: addShape, move, changeColor, changeScale, remove, snapshot;
	d. Controller calls AlbumModel.display() and gets List<Snapshot>;
	c. Controller pass List<Snapshot>, Dimension canvasDimension and String output filename/frame title as parameters to GraphicalView or WebView class;
	e. Controller calls IView.display();
	d. GraphicalView or WebView class display List<Snapshot> to user.

2. Changes of Model Package:
	a. Class: albumModel -- Change return type of albumModel.display() from String to List<Snapshot>;
	b. Class: shapeCollection -- Add method reset();
	c. Class: IShape -- Add methods getPoint(), getColor(), getScale();
	d. Class: Point, Color, Scale -- Change from package private class to public class

3. ViewHelper:
	a. GraphicalViewHelper and WebViewHelper are two concrete classes of ViewHelper;
	b. ViewHelper is a helper class to implement details of graphical view and web view;
	c. GraphicalViewHelper has a GraphicalPanel to draw shapes and write texts.
	
