package com.onedeveloperstudio.core.common.util;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import java.io.FilterInputStream;
import java.io.InputStream;

public class XMLUtil {

  public static Document createDocument(InputStream input) {
    DOMParser parser = null;
    try {
      parser = getLargeParser(input.available());
      parser.parse(new InputSource(new FilterInputStream(input) {
        public void close() {
          // disable close. Because createDocument calls close, but it shoudn't do it.
        }
      }));
      return parser.getDocument();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static DOMParser getLargeParser(int size) {
    DOMParser parser = new DOMParser();
    try {
      parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
      parser.setProperty("http://apache.org/xml/properties/input-buffer-size", size);
    } catch (SAXNotRecognizedException e) {
      throw new IllegalArgumentException();
    } catch (SAXNotSupportedException e) {
      throw new IllegalArgumentException();
    }
    return parser;
  }

  /**
   * Возвращает первый элемент вложенный в элемент el
   * Поиск осуществляется только на одном уровне вложенности
   *
   * @param el элемент внутри которого осуществляется поиск
   * @return найденный элемент, null если ничего не найдено
   */
  public static Element getChildElement(Element el) {
    Node node = el.getFirstChild();
    while (node != null) {
      if (node.getNodeType() == Node.ELEMENT_NODE)
        return (Element) node;
      node = node.getNextSibling();
    }
    return null;
  }

  /**
   * Возвращает первый элемент с именем tagName находящийся ниже элемента el
   * Поиск осуществляется только на уровне элемента el
   *
   * @param el элемент на уровене которого осуществляется поиск
   * @return найденный элемент, null если ничего не найдено
   */
  public static Element getNextElement(Element el) {
    Node node = el.getNextSibling();
    while (node != null) {
      if (node.getNodeType() == Node.ELEMENT_NODE)
        return (Element) node;
      node = node.getNextSibling();
    }
    return null;
  }

}
