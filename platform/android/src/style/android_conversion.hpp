#pragma once

#include "value.hpp"

#include <mbgl/platform/log.hpp>
#include <mbgl/style/conversion.hpp>
#include <mbgl/util/feature.hpp>
#include <mbgl/util/optional.hpp>

#include <jni/jni.hpp>

namespace mbgl {
namespace style {
namespace conversion {


//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

inline bool isUndefined(const mbgl::android::Value& value) {
    return value.isNull();
}

inline bool isArray(const mbgl::android::Value& value) {
    return value.isArray();
}

inline bool isObject(const mbgl::android::Value& value) {
    return value.isObject();
}

inline std::size_t arrayLength(const mbgl::android::Value& value) {
    return value.getLength();;
}

inline mbgl::android::Value arrayMember(const mbgl::android::Value& value, std::size_t i) {
    return value.get(i);
}

inline optional<mbgl::android::Value> objectMember(const mbgl::android::Value& value, const char* key) {
    mbgl::android::Value member = value.get(key);

    if (!member.isNull()) {
        return member;
    } else {
        return {};
    }
}

template <class Fn>
optional<Error> eachMember(const mbgl::android::Value& value, Fn&& fn) {
    //TODO
    mbgl::Log::Warning(mbgl::Event::Android, "eachMember not implemented");
    return {};
}

inline optional<bool> toBool(const mbgl::android::Value& value) {
    if (value.isBool()) {
        return value.toBool();
    } else {
        return {};
    }
}

inline optional<float> toNumber(const mbgl::android::Value& value) {
    if (value.isNumber()) {
        return value.toNumber();
    } else {
        return {};
    }
}

inline optional<std::string> toString(const mbgl::android::Value& value) {
    if (value.isString()) {
        return value.toString();
    } else {
        return {};
    }
}

inline optional<Value> toValue(const mbgl::android::Value& value) {
    if (value.isBool()) {
        return { value.toBool() };
    } else if (value.isString()) {
        return { value.toString() };
    } else if (value.isNumber()) {
       return { value.toNumber() };
    } else {
        return {};
    }
}

} // namespace conversion
} // namespace style
} // namespace mbgl