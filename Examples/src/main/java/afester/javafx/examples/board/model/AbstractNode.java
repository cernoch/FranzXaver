package afester.javafx.examples.board.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public abstract class AbstractNode {
    private Point2D pos;

    protected Net net;
    public List<AbstractWire> traceStarts = new ArrayList<>();
    public List<AbstractWire> traceEnds = new ArrayList<>();

    protected int id;	// currently only required for serialization and deserialization

    private ObjectProperty<Color> color = new SimpleObjectProperty<Color>(Color.LIGHTGRAY);
    public ObjectProperty<Color> colorProperty() { return color; }
    public void setColor(Color col) { color.setValue(col); }
    public Color getColor() { return color.get(); }


    public AbstractNode(Net net, Point2D pos) {
        this.pos = pos;
        this.net = net;
    }

    public void setId(int i) {
       id = i;
    }

	public Point2D getPos() {
        return pos;
    }
	
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public void addStart(AbstractWire wire) {
        traceStarts.add(wire);
    }

    public void addEnd(AbstractWire wire) {
        traceEnds.add(wire);
    }

//    public void moveTraces2(double x, double y) {
//        // TODO: This requires a reference to a real "Trace" object.
//        // Depending on the Trace type, it might also require to move the other coordinates ....
//        for (Line l : traceStarts) {
//            l.setStartX(x);
//            l.setStartY(y);
//        }
//
//        for (Line l : traceEnds) {
//            l.setEndX(x);
//            l.setEndY(y);
//        }
//        
//    }
//
//	@Override
//	public void setSelected(boolean isSelected) {
//		if (isSelected) {
//			setFill(Color.DARKRED);
//		} else {
//			setFill(null);
//		}
//	}
//
//    public void setSelected(boolean isSelected, Color col) {
//        if (isSelected) {
//            setFill(Color.BLUE); // col);
//        } else {
//            setFill(null);
//        }
//    }
//
//

    /**
     * @return A list of all edges which are connected to this node.
     */
    public List<AbstractWire> getEdges() {
        List<AbstractWire> result = new ArrayList<>();

        result.addAll(traceStarts);
        result.addAll(traceEnds);

        return result;
    }


    /**
     * From a collection of nodes, get the one which is nearest to this one.
     *
     * @param nodeList The list of nodes from which to get the nearest one.
     * @return The node which is the nearest to this one.
     */
    public AbstractNode getNearestNode(List<AbstractNode> nodeList) {
        double minDist = Double.MAX_VALUE;
        AbstractNode result = null;
        for (AbstractNode node: nodeList) {
            double dist = node.getPos().distance(getPos());
            if (dist < minDist) {
                result = node;
                minDist = dist;
            }
        }

        return result;
    }

    public Net getNet() {
        return net;
    }
}
