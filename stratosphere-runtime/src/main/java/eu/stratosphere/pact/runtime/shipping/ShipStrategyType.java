/***********************************************************************************************************************
 * Copyright (C) 2010-2013 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 **********************************************************************************************************************/

package eu.stratosphere.pact.runtime.shipping;

/**
 * Enumeration defining the different shipping types of the output, such as local forward, re-partitioning by hash,
 * or re-partitioning by range.
 */
public enum ShipStrategyType
{
	/**
	 * Constant used as an indicator for an unassigned ship strategy.
	 */
	NONE(false, false, false),
	
	/**
	 * Forwarding the data locally in memory.
	 */
	FORWARD(false, false, false),
	
	/**
	 * Repartitioning the data randomly, typically when the degree of parallelism between two nodes changes.
	 */
	PARTITION_RANDOM(true, true, false),
	
	/**
	 * Repartitioning the data deterministically through a hash function.
	 */
	PARTITION_HASH(true, true, true),
	
	/**
	 * Repartitioning the data within a local instance with a hash function. Happens for example when the
	 * intra-node degree-of-parallelism is increased. 
	 */
	PARTITION_LOCAL_HASH(false, true, true),
	
	/**
	 * Partitioning the data in ranges according to a total order.
	 */
	PARTITION_RANGE(true, true, true),
	
	/**
	 * Replicating the data set to all instances.
	 */
	BROADCAST(true, true, false);
	
	// --------------------------------------------------------------------------------------------
	
	private final boolean isNetwork;
	
	private final boolean compensatesForLocalDOPChanges;
	
	private final boolean requiresComparator;
	
	
	private ShipStrategyType(boolean network, boolean compensatesForLocalDOPChanges, boolean requiresComparator) {
		this.isNetwork = network;
		this.compensatesForLocalDOPChanges = compensatesForLocalDOPChanges;
		this.requiresComparator = requiresComparator;
	}
	
	public boolean isNetworkStrategy() {
		return this.isNetwork;
	}
	
	public boolean compensatesForLocalDOPChanges() {
		return this.compensatesForLocalDOPChanges;
	}
	
	public boolean requiresComparator() {
		return this.requiresComparator;
	}
}
