package codes;

public class LinkedGrid {

	private static Node root;
	private int dimension = 6;
	private int solutions = 0;

	public LinkedGrid() {
		Node temp = new Node(0); // First Node
		root = temp;
		Node Marker = root;
		for (int x = 0; x < dimension - 1; x++) { // First Row
			temp = new Node(0);
			Marker.setRight(temp);
			temp.setLeft(Marker);
			Marker = temp;
		}
		// First node in second row
		Marker = root;
		Node rowMarker = root;
		for (int y = 0; y < dimension - 1; y++) {
			temp = new Node(0);
			temp.setUp(rowMarker);
			rowMarker.setDown(temp);
			rowMarker = temp;
			Marker = rowMarker;

			for (int x = 0; x < dimension - 1; x++) { // every row after first
				temp = new Node(0);
				Marker.setRight(temp);
				temp.setLeft(Marker);
				temp.setUp(temp.getLeft().getUp().getRight());
				temp.getLeft().getUp().getRight().setDown(temp);
				Marker = temp;
			}
		}
	}

	public void display() {
		Node temp = root;
		Node rowMarker = root;

		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				System.out.printf("%3d", temp.getData());
				temp = temp.getRight();
			}
			System.out.println();
			temp = rowMarker.getDown();
			rowMarker = temp;
		}
		System.out.println();
	}

	// public Node nodeAt(int x, int y) {
	//
	// }

	public Node nodeAt(int x, int y) {
		Node n = root;

		for (int i = 0; i < x; i++) {
			n = n.getRight();
		}

		for (int i = 0; i < y; i++) {
			n = n.getDown();
		}

		return n;
	}

	private int counter = 1;

	public void search(int x, int y) {

		int nextX = 0;
		int nextY = 0;

		for (int k = 1; k < 3; k++) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (i == 0) {
						nextX = x + (3 - k);
					} else if (i == 1) {
						nextX = x - (3 - k);
					}
					if (j == 0) {
						nextY = y - k;
					} else if (j == 1) {
						nextY = y + k;
					}
//					System.out.println(nextX+" "+nextY);
					if (nextX < dimension && nextX >= 0 && nextY < dimension && nextY >= 0) {
						if (nodeAt(nextX, nextY).getData() == 0) {
							nodeAt(x, y).setData(counter);
							counter++;
								search(nextX, nextY);
							// display();
						}
					}
				}
			}
		}
		if (counter == (int) (Math.pow(dimension, 2))) {
			display();
			solutions++;
		}
		nodeAt(x, y).setData(0);
		counter--;
	}
	// i tried
	// static LinkedGrid ll = new LinkedGrid();
	// static int counter = 1;
	// static int dirCounter = 0;
	// static Node next;
	//
	// public static void KnightsTour(Node current) {
	//
	//// current.setData(counter);
	//
	// switch (dirCounter) {
	// case 0:
	// if (current.getDown() != null && current.getDown().getDown() != null
	// && current.getDown().getDown().getRight() != null
	// && current.getDown().getDown().getRight().getData() == 0) {
	// counter++;
	// next = current.getDown().getDown().getRight();
	// next.setData(counter);
	// break;
	//
	//
	// }
	// case 1:
	// if (current.getRight() != null && current.getRight().getRight() != null
	// && current.getRight().getRight().getDown() != null
	// && current.getRight().getRight().getDown().getData() == 0) {
	// counter++;
	// next = current.getRight().getRight().getDown();
	// next.setData(counter);
	// break;
	// }
	// case 2:
	// if (current.getRight() != null && current.getRight().getRight() != null
	// && current.getRight().getRight().getUp() != null
	// && current.getRight().getRight().getUp().getData() == 0) {
	// counter++;
	// next = current.getRight().getRight().getUp();
	// next.setData(counter);
	// break;
	//
	// }
	// case 3:
	// if (current.getUp() != null && current.getUp().getUp() != null &&
	// current.getUp().getUp().getRight() != null
	// && current.getUp().getUp().getRight().getData() == 0) {
	// counter++;
	// next = current.getUp().getUp().getRight();
	// next.setData(counter);
	// break;
	//
	// }
	// case 4:
	// if (current.getLeft() != null && current.getLeft().getLeft() != null
	// && current.getLeft().getLeft().getUp() != null
	// && current.getLeft().getLeft().getUp().getData() == 0) {
	// counter++;
	// next = current.getLeft().getLeft().getUp();
	// next.setData(counter);
	// break;
	//
	// }
	// case 5:
	// if (current.getUp() != null && current.getUp().getUp() != null &&
	// current.getUp().getUp().getLeft() != null
	// && current.getUp().getUp().getLeft().getData() == 0) {
	// counter++;
	// next = current.getUp().getUp().getLeft();
	// next.setData(counter);
	// break;
	//
	// }
	// case 6:
	// if (current.getLeft() != null && current.getLeft().getLeft() != null
	// && current.getLeft().getLeft().getDown() != null
	// && current.getLeft().getLeft().getDown().getData() == 0) {
	// counter++;
	// next = current.getLeft().getLeft().getDown();
	// next.setData(counter);
	// break;
	//
	//
	// }
	// case 7:
	// if (current.getDown() != null && current.getDown().getDown() != null
	// && current.getDown().getDown().getLeft() != null
	// && current.getDown().getDown().getLeft().getData() == 0) {
	// counter++;
	// next = current.getDown().getDown().getLeft();
	// next.setData(counter);
	// break;
	//
	// }
	// default: {
	// counter--;
	// current.setData(0);
	// if (dirCounter < 8)
	// dirCounter++;
	// next = current;
	// }
	//
	// }
	// KnightsTour(next);
	// ll.display();
	//
	// }

	public static void main(String[] args) {
		LinkedGrid ll = new LinkedGrid();
		ll.search(0, 0);
	}
}
