package com.px.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeKey implements WritableComparable<CompositeKey> {
    private String date;
    private int afre;

    public CompositeKey() {
    }

    public CompositeKey(String date, int afre) {
        this.date = date;
        this.afre = afre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAfre() {
        return afre;
    }

    public void setAfre(int afre) {
        this.afre = afre;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.date);
        dataOutput.writeInt(this.afre);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.date=dataInput.readUTF();
        this.afre=dataInput.readInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;

        CompositeKey that = (CompositeKey) o;

        if (getAfre() != that.getAfre()) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + getAfre();
        return result;
    }


    @Override
    public int compareTo(CompositeKey o) {
        if (0 != DateTime.parse(this.getDate(), DateTimeFormat.forPattern("YYYY.MM")).compareTo(DateTime.parse(o.getDate(), DateTimeFormat.forPattern("YYYY.MM")))) {
            return DateTime.parse(this.getDate(), DateTimeFormat.forPattern("YYYY.MM")).compareTo(DateTime.parse(o.getDate(), DateTimeFormat.forPattern("YYYY.MM")));
        }
        return this.getAfre() == o.getAfre() ? 0 : (this.getAfre() > o.getAfre() ? 1 : -1);
    }
}
