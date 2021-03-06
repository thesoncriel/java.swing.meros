//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.03.15 at 07:35:38 ���� KST 
//


package member;

import java.net.Proxy.Type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for horseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="horseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AmericanQuarter"/>
 *     &lt;enumeration value="AmericanSaddle"/>
 *     &lt;enumeration value="AngloArab"/>
 *     &lt;enumeration value="Appaloosa"/>
 *     &lt;enumeration value="Arabian"/>
 *     &lt;enumeration value="Asian"/>
 *     &lt;enumeration value="ClevelandBay"/>
 *     &lt;enumeration value="EnglishHackney"/>
 *     &lt;enumeration value="GermanHolstein"/>
 *     &lt;enumeration value="Hanoverian"/>
 *     &lt;enumeration value="Lipizzaner"/>
 *     &lt;enumeration value="MissouriFoxTrotting"/>
 *     &lt;enumeration value="Morgan"/>
 *     &lt;enumeration value="Noriker"/>
 *     &lt;enumeration value="Percheron"/>
 *     &lt;enumeration value="Shire"/>
 *     &lt;enumeration value="Standardbred"/>
 *     &lt;enumeration value="Suffolk"/>
 *     &lt;enumeration value="TennesseeWalking"/>
 *     &lt;enumeration value="Thoroughbred"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "horseType")
@XmlEnum
public enum HorseType {

    @XmlEnumValue("AmericanQuarter")
    AMERICAN_QUARTER("AmericanQuarter"),
    @XmlEnumValue("AmericanSaddle")
    AMERICAN_SADDLE("AmericanSaddle"),
    @XmlEnumValue("AngloArab")
    ANGLO_ARAB("AngloArab"),
    @XmlEnumValue("Appaloosa")
    APPALOOSA("Appaloosa"),
    @XmlEnumValue("Arabian")
    ARABIAN("Arabian"),
    @XmlEnumValue("Asian")
    ASIAN("Asian"),
    @XmlEnumValue("ClevelandBay")
    CLEVELAND_BAY("ClevelandBay"),
    @XmlEnumValue("EnglishHackney")
    ENGLISH_HACKNEY("EnglishHackney"),
    @XmlEnumValue("GermanHolstein")
    GERMAN_HOLSTEIN("GermanHolstein"),
    @XmlEnumValue("Hanoverian")
    HANOVERIAN("Hanoverian"),
    @XmlEnumValue("Lipizzaner")
    LIPIZZANER("Lipizzaner"),
    @XmlEnumValue("MissouriFoxTrotting")
    MISSOURI_FOX_TROTTING("MissouriFoxTrotting"),
    @XmlEnumValue("Morgan")
    MORGAN("Morgan"),
    @XmlEnumValue("Noriker")
    NORIKER("Noriker"),
    @XmlEnumValue("Percheron")
    PERCHERON("Percheron"),
    @XmlEnumValue("Shire")
    SHIRE("Shire"),
    @XmlEnumValue("Standardbred")
    STANDARDBRED("Standardbred"),
    @XmlEnumValue("Suffolk")
    SUFFOLK("Suffolk"),
    @XmlEnumValue("TennesseeWalking")
    TENNESSEE_WALKING("TennesseeWalking"),
    @XmlEnumValue("Thoroughbred")
    THOROUGHBRED("Thoroughbred");
    private final String value;

    HorseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HorseType fromValue(String v) {
        for (HorseType c: HorseType.values()) {
            if (c.value.toUpperCase().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
    /*
    public static String toKorean(HorseType type){
    	return type;
    }
	*/
}
