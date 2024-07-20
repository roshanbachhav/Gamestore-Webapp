package com.gamestore.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class INDConst {

    public final static String India = "India";

    public final static Map<String, String> indiaStates;
    public final static List<String> stateCode;
    public final static List<String> stateName;

    static {
        indiaStates = new HashMap<>();
        indiaStates.put("AP", "Andhra Pradesh");
        indiaStates.put("AR", "Arunachal Pradesh");
        indiaStates.put("AS", "Assam");
        indiaStates.put("BR", "Bihar");
        indiaStates.put("CG", "Chhattisgarh");
        indiaStates.put("GA", "Goa");
        indiaStates.put("GJ", "Gujarat");
        indiaStates.put("HR", "Haryana");
        indiaStates.put("HP", "Himachal Pradesh");
        indiaStates.put("JH", "Jharkhand");
        indiaStates.put("KA", "Karnataka");
        indiaStates.put("KL", "Kerala");
        indiaStates.put("MP", "Madhya Pradesh");
        indiaStates.put("MH", "Maharashtra");
        indiaStates.put("MN", "Manipur");
        indiaStates.put("ML", "Meghalaya");
        indiaStates.put("MZ", "Mizoram");
        indiaStates.put("NL", "Nagaland");
        indiaStates.put("OD", "Odisha");
        indiaStates.put("PB", "Punjab");
        indiaStates.put("RJ", "Rajasthan");
        indiaStates.put("SK", "Sikkim");
        indiaStates.put("TN", "Tamil Nadu");
        indiaStates.put("TG", "Telangana");
        indiaStates.put("TR", "Tripura");
        indiaStates.put("UP", "Uttar Pradesh");
        indiaStates.put("UK", "Uttarakhand");
        indiaStates.put("WB", "West Bengal");

        indiaStates.put("AN", "Andaman and Nicobar Islands");
        indiaStates.put("CH", "Chandigarh");
        indiaStates.put("DN", "Dadra and Nagar Haveli and Daman and Diu");
        indiaStates.put("LD", "Lakshadweep");
        indiaStates.put("DL", "Delhi");
        indiaStates.put("PY", "Puducherry");
        indiaStates.put("LA", "Ladakh");
        indiaStates.put("JK", "Jammu and Kashmir");

        stateCode = new ArrayList<>(indiaStates.keySet());
        stateName = new ArrayList<>(indiaStates.values());

        Collections.sort(stateCode); // Ensure the state codes are sorted
    }
}
