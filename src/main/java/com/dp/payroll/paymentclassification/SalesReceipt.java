package com.dp.payroll.paymentclassification;

public class SalesReceipt {
    private double sales;

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getSales() {
        return sales;
    }

    public SalesReceipt(double sales) {
        setSales(sales);
    }

}
