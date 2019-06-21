package com.anupam.symboltables;

public interface IHashTable<K,V> {
	
	V get(K key);

	void put(K key, V value);
	
	void remove(K key);
	
	Iterable<K> keys();
	
	Iterable<V> values();
}
