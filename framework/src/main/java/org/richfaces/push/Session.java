/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.push;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Session represents user’s subscription to a set of topics. This set is immutable, so new session should be
 * created if user wants to add/remove subscriptions to some topics.
 * </p>
 *
 * <p>
 * Session does multiplexing for messages from several topics, so that only a single client connection is required to for data
 * transfer.
 * </p>
 *
 * <p>
 * When session is created, it is getting unique UUID identifier, that client uses to communicate to that session.
 * </p>
 *
 * <p>
 * When client is subscribed to a set of topics associated with some session, session fires SessionPreSubscriptionEvent, so that
 * application developer can control subscriptions according to access rights.
 * </p>
 *
 * <p>
 * Session is kept alive for 5 minutes since the last time it has been accessed. Note that push can work in either polling (long
 * polling) and persistent connection (WebSocket) modes, so session should be kept-alive correctly in both cases.
 * </p>
 *
 * @author Nick Belaevski
 *
 */
public interface Session {

    int getMaxInactiveInterval();

    long getLastAccessedTime();

    String getId();

    Collection<TopicKey> getSuccessfulSubscriptions();

    Map<TopicKey, String> getFailedSubscriptions();

    void subscribe(String[] topics);

    void connect(Request request) throws Exception;

    void disconnect() throws Exception;

    void invalidate();

    void push(TopicKey topicKey, String serializedData);

    Collection<MessageData> getMessages();

    void clearBroadcastedMessages(long sequenceNumber);
}
