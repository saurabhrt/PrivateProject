package com.example.zues.healthok.model;


import java.util.ArrayList;

public class LabOrder extends OrderBase {
    private int labResultImageId = 0;
    private int prescriptionImageId = 0;
    private String description;
    //	private int orderId;
    private int labOrderId;
    private int id;


    public int getId() {
        return labOrderId;
    }

    public void setId(int id) {
        this.id = id;
        this.labOrderId = id;
    }

    private ArrayList<LabOrderDetail> labOrderDetail;

    public int getLabResultImageId() {
        return labResultImageId;
    }

    public void setLabResultImageId(int labResultImageId) {
        this.labResultImageId = labResultImageId;
    }

    public int getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(int prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setOrderDescription(description);

    }

    /*	public int getOrderId() {
            return orderId;
        }
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
    */
    public int getLabOrderId() {
        return labOrderId;
    }

    public void setLabOrderId(int labOrderId) {
        this.labOrderId = labOrderId;
    }

    public ArrayList<LabOrderDetail> getLabOrderDetail() {
        return labOrderDetail;
    }

    public void setLabOrderDetail(ArrayList<LabOrderDetail> labOrderDetail) {
        this.labOrderDetail = labOrderDetail;
    }

    public LabOrder() {
    }
    /*
	public LabOrder(  int laborderId,int orderId,int prescriptionimageId,int labresultimageId,String description)
	{
		super();
		this.labResultImageId = labResultImageId;
		this.prescriptionImageId=prescriptionImageId;
		this.description = description;
		this.orderId=orderId;
		this.labOrderId=labOrderId;
		//this.CashbackBonusApplied=CashbackBonusApplied;
		}

	*/


}
