package com.hyssop.framework.util.http;


import com.google.common.base.Preconditions;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author haoyang.shi
 * @date 2018/11/8
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class HttpResponse {
    private Integer code;

    @Setter
    private byte[] body;
    @Setter
    private Header[] headerArr;

    private Map<String, List<String>> headers;

    public Optional<List<String>> getHeader(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name));

        return Optional.ofNullable(this.headers.get(name));
    }

    public Map<String, List<String>> getAllHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public String getStringBody() {
        ContentType contentType = ContentType.APPLICATION_JSON;

        Optional<List<String>> contentTypeOp = getHeader(HttpHeaders.CONTENT_TYPE);

        if (contentTypeOp.isPresent()) {
            contentType = ContentType.parse(contentTypeOp.get().get(0));
        }

        return new String(body, contentType.getCharset() == null ? StandardCharsets.UTF_8 : contentType.getCharset());
    }

    @Override
    public String toString() {

        if (ArrayUtils.isNotEmpty(body)) {
            return "HttpResponse{" +
                    "code=" + code +
                    ", body=" + new String(body, StandardCharsets.UTF_8) +
                    ", headers=" + headers +
                    '}';
        }

        return "HttpResponse{" +
                "code=" + code +
//                ", body=" + Arrays.toString(body) +
                ", headers=" + headers +
                '}';
    }
}
