// This file is generated. Edit android/platform/scripts/generate-style-code.js, then run `make style-code-android`.

#include "background_layer.hpp"

#include <string>

//XXX
#include <mbgl/platform/log.hpp>

namespace mbgl {
namespace android {

    BackgroundLayer::BackgroundLayer(jni::JNIEnv& env, jni::String layerId)
        : Layer(env, std::make_unique<mbgl::style::BackgroundLayer>(jni::Make<std::string>(env, layerId))) {
        mbgl::Log::Debug(mbgl::Event::JNI, "BackgroundLayer constructed, owning reference");
    }

    BackgroundLayer::BackgroundLayer(mbgl::Map& map, mbgl::style::BackgroundLayer& coreLayer)
        : Layer(map, coreLayer) {

        mbgl::Log::Debug(mbgl::Event::JNI, "BackgroundLayer Non-owning reference constructor");
    }

    BackgroundLayer::~BackgroundLayer() = default;

    jni::Class<BackgroundLayer> BackgroundLayer::javaClass;

    jni::jobject* BackgroundLayer::createJavaPeer(jni::JNIEnv& env) {
        static auto constructor = BackgroundLayer::javaClass.template GetConstructor<jni::jlong>(env);
        return BackgroundLayer::javaClass.New(env, constructor, reinterpret_cast<jni::jlong>(this));
    }

    void BackgroundLayer::registerNative(jni::JNIEnv& env) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Registering native background layer");

        //Lookup the class
        BackgroundLayer::javaClass = *jni::Class<BackgroundLayer>::Find(env).NewGlobalRef(env).release();

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        //Register the peer
        jni::RegisterNativePeer<BackgroundLayer>(
            env, BackgroundLayer::javaClass, "nativePtr",
            std::make_unique<BackgroundLayer, JNIEnv&, jni::String>,
            "initialize",
            "finalize"
        );

    }

} // namespace android
} // namespace mbgl
