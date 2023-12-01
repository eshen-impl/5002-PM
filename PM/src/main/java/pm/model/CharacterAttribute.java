package pm.model;

public class CharacterAttribute {
protected Character character;
protected String attributes;
protected int attributeValue;
public CharacterAttribute(Character character, String attributes, int attributeValue) {
	this.character = character;
	this.attributes = attributes;
	this.attributeValue = attributeValue;
}
public Character getCharacter() {
	return character;
}
public void setCharacter(Character character) {
	this.character = character;
}
public String getAttributes() {
	return attributes;
}
public void setAttributes(String attributes) {
	this.attributes = attributes;
}
public int getAttributeValue() {
	return attributeValue;
}
public void setAttributeValue(int attributeValue) {
	this.attributeValue = attributeValue;
}


}
