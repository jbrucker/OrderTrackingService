package shop.entity;

public enum StatusType {
	ORDER_RETRIEVE("Order retrieved")
	,ORDER_PICK("Order picked")
	,ORDER_READY("Ready for shipment")
	,ORDER_SCAN("Scanned")
	,ORDER_TRANSIT("In transit");
	
	private String type;
	
	private StatusType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
