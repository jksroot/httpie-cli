package io.httpie.cli;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RequestItems {
    public final Dicts.HTTPHeadersDict headers = new Dicts.HTTPHeadersDict();
    public final Object data;
    public final Dicts.RequestQueryParamsDict params = new Dicts.RequestQueryParamsDict();
    public final Dicts.RequestFilesDict files = new Dicts.RequestFilesDict();
    public final Dicts.MultipartRequestDataDict multipartData = new Dicts.MultipartRequestDataDict();
    public final Constants.RequestType requestType;
    public final boolean isJson;

    public RequestItems(Constants.RequestType requestType) {
        this.requestType = requestType;
        this.isJson = requestType == null || requestType == Constants.RequestType.JSON;
        this.data = isJson ? new Dicts.RequestJSONDataDict() : new Dicts.RequestDataDict();
    }

    public static RequestItems fromArgs(List<ArgTypes.KeyValueArg> args, Constants.RequestType requestType) {
        RequestItems instance = new RequestItems(requestType);
        for (ArgTypes.KeyValueArg arg : args) {
            instance.processArg(arg);
        }
        return instance;
    }

    private void processArg(ArgTypes.KeyValueArg arg) {
        String sep = arg.sep;
        String key = arg.key;
        String value = arg.value;

        switch (sep) {
            case Constants.SEPARATOR_HEADER -> headers.add(key, value);
            case Constants.SEPARATOR_HEADER_EMPTY -> headers.add(key, null);
            case Constants.SEPARATOR_QUERY_PARAM -> params.add(key, value);
            case Constants.SEPARATOR_DATA_STRING -> {
                if (isJson) {
                    ((Dicts.RequestJSONDataDict) data).put(key, value);
                } else {
                    ((Dicts.RequestDataDict) data).add(key, value);
                }
                if (requestType == Constants.RequestType.MULTIPART) {
                    multipartData.add(key, value);
                }
            }
            case Constants.SEPARATOR_FILE_UPLOAD -> {
                // Simplified file upload processing
                files.add(key, value);
                if (requestType == Constants.RequestType.MULTIPART) {
                    multipartData.add(key, value);
                }
            }
            // Add more cases as needed
        }
    }

    public boolean hasData() {
        if (data instanceof Map) {
            return !((Map<?, ?>) data).isEmpty();
        } else if (data instanceof Dicts.BaseMultiDict) {
            return !((Dicts.BaseMultiDict) data).getData().isEmpty();
        }
        return false;
    }
}
