/**
 * Copyright 2011 Damian Momot
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.simplelrucache;

import java.util.concurrent.Callable;

/**
 * Threadsafe synchronized implementation of LruCache based on LinkedHashMap.
 * Threadsafety is provided by method synchronization. This cache implementation
 * should be used with low number of threads.
 * 
 * @param <K> key type
 * @param <V> value type
 * @author Damian Momot
 */
public class SynchronizedLruCache<K, V> extends BaseLruCache<K, V> {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    private CapacityLruLinkedHashMap<K, LruCacheEntry<V>> cacheMap;
    
    /**
     * Creates new SynchronizedLruCache
     * 
     * @param capacity
     * @param ttl
     * @param initialCapacity
     * @param loadFactor 
     */
    public SynchronizedLruCache(int capacity, long ttl, int initialCapacity, float loadFactor) {
        super(ttl);
        
        cacheMap = new CapacityLruLinkedHashMap<K, LruCacheEntry<V>>(capacity, initialCapacity, loadFactor);
    }
    
    /**
     * Creates new SynchronizedLruCache with DEFAULT_LOAD_FACTOR
     * 
     * @param capacity
     * @param ttl
     * @param initialCapacity 
     */
    public SynchronizedLruCache(int capacity, long ttl, int initialCapacity) {
        this(capacity, ttl, initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    
    /**
     * Creates new SynchronizedLruCache with DEFAULT_LOAD_FACTOR and
     * DEFAULT_INITIAL_CAPACITY
     * 
     * @param capacity
     * @param ttl 
     */
    public SynchronizedLruCache(int capacity, long ttl) {
        this(capacity, ttl, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    
    @Override
    synchronized public void clear() {
        cacheMap.clear();
    }
    
    @Override
    synchronized public boolean contains(K key) {
        return super.contains(key);
    }
    
    @Override
    synchronized public V get(K key) {
        return super.get(key);
    }
    
    @Override
    synchronized public V get(K key, Callable<V> callback) throws Exception {
        return super.get(key, callback);
    }
    
    @Override
    synchronized public V get(K key, Callable<V> callback, long ttl) throws Exception {
        return super.get(key, callback, ttl);
    }
    
    @Override
    synchronized public int getCapacity() {
        return cacheMap.getCapacity();
    }

    @Override
    protected LruCacheEntry<V> getEntry(K key) {
        return cacheMap.get(key);
    }
    
    @Override
    synchronized public int getSize() {
        return cacheMap.size();
    }
    
    @Override
    synchronized public boolean isEmpty() {
        return super.isEmpty();
    }
    
    @Override
    synchronized public void put(K key, V value) {
        super.put(key, value);
    }
    
    @Override
    synchronized public void put(K key, V value, long ttl) {
        super.put(key, value, ttl);
    }

    @Override
    protected void putEntry(K key, LruCacheEntry<V> entry) {
        cacheMap.put(key, entry);
    }

    @Override
    synchronized public void remove(K key) {
        cacheMap.remove(key);
    }
}