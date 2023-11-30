package milestone.model;

public class CharacterAttribute {
protected Character character;
protected Attributes attributes;
protected int attributeValue;
public CharacterAttribute(Character character, Attributes attributes, int attributeValue) {
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
public Attributes getAttributes() {
	return attributes;
}
public void setAttributes(Attributes attributes) {
	this.attributes = attributes;
}
public int getAttributeValue() {
	return attributeValue;
}
public void setAttributeValue(int attributeValue) {
	this.attributeValue = attributeValue;
}


}
