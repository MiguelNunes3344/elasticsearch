/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.index.reindex;

import org.elasticsearch.action.ActionType;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.TransportSearchAction;
import org.elasticsearch.client.internal.ElasticsearchClient;

public class UpdateByQueryRequestBuilder extends AbstractBulkIndexByScrollRequestBuilder<
    UpdateByQueryRequest,
    UpdateByQueryRequestBuilder> {

    public UpdateByQueryRequestBuilder(ElasticsearchClient client, ActionType<BulkByScrollResponse> action) {
        this(client, action, new SearchRequestBuilder(client, TransportSearchAction.TYPE));
    }

    private UpdateByQueryRequestBuilder(ElasticsearchClient client, ActionType<BulkByScrollResponse> action, SearchRequestBuilder search) {
        super(client, action, search, new UpdateByQueryRequest(search.request()));
    }

    @Override
    protected UpdateByQueryRequestBuilder self() {
        return this;
    }

    @Override
    public UpdateByQueryRequestBuilder abortOnVersionConflict(boolean abortOnVersionConflict) {
        request.setAbortOnVersionConflict(abortOnVersionConflict);
        return this;
    }

    public UpdateByQueryRequestBuilder setPipeline(String pipeline) {
        request.setPipeline(pipeline);
        return this;
    }
}
