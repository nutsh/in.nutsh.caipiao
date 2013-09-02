package com.nutsh;

import java.util.ArrayList;
import java.util.List;


public class MathLottery {
	
	public List<String> zuhe(String[] nums , int elementNos){
		Group group = new Group(elementNos,nums);
		group.go();
		List<Integer> elements = group.elements;
		return group.results;
	}
	public static void main(String[] args) {
		new MathLottery().zuhe(new String[]{"11","12","13","14","15","16","17","18","19","20"}, 5);
	}
	
}

class Group{
	String[] nums ;
	int elementNos = 10;

	List<Integer> elements = new ArrayList<Integer>();

	List<String> results = new ArrayList<String>();
	
	public Group(int elementNos,String[] nums ){
		this.nums = nums;
		this.elementNos = nums.length;
		for (int i = 0; i < elementNos; i++) {
			elements.add(i,i);
		}
		printAndParseResult(elements);
	}
	
	public void go(){
		Element element;
		while ((element = getGrowElement()) != null) {
			reOrderGroup(element.index,element.number);
			printAndParseResult(elements);
		}
	}

	private void printAndParseResult(List<Integer> elements2) {
		String s = "";
		for (Integer integer : elements2) {
			s  = s+nums[integer]+":";
		}
		s = s.substring(0,s.length()-1);
		System.out.println(s);
		results.add(s);
	}



	private void reOrderGroup(int index, int number) {
		elements.set(index, number+1);
		for(int i=index+1;i<elements.size();i++){
			elements.set(i, elements.get(i-1)+1);
		}
	}

	private Element getGrowElement() {
		int index = elements.size()-1 ;
		while(index>=0){
			if(!isMeetCeiling(elements, index)){
				return new Element(index, elements.get(index));
			}
			index--;
		}
		return null;
	}
	
	boolean isMeetCeiling(List<Integer> elements ,int index){
		int top ;
		if(index == elements.size()-1){
			top = elementNos-1;
		}else{
			top = elements.get(index+1)-1;
		}
		return elements.get(index)>=top;
	}
	
}



class Element{
	int index;
	int number;
	public Element(int index, int number) {
		this.index = index;
		this.number = number;
	}
}
