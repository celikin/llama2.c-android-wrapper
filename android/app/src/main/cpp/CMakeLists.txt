# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.
# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("inference")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#
# In this top level CMakeLists.txt, ${CMAKE_PROJECT_NAME} is used to define
# the target library name; in the sub-module's CMakeLists.txt, ${PROJECT_NAME}
# is preferred for the same purpose.
#
# In order to load a library into your app from Java/Kotlin, you must call
# System.loadLibrary() and pass the name of the library defined here;
# for GameActivity/NativeActivity derived applications, the same library name must be
# used in the AndroidManifest.xml file.

#for inference-gpu uncomment this block

#set(OPTIMIZATION_FLAGS -Ofast -funroll-all-loops -Wall -Wextra)
#add_library(${CMAKE_PROJECT_NAME} SHARED inference-gpu.c)
#find_library(GLESv2-lib GLESv2 )
#find_library(GLESv3-lib GLESv3 )
#find_library(EGL-lib EGL )
#target_link_libraries(${CMAKE_PROJECT_NAME} android log -lm ${GLESv2-lib} ${GLESv3-lib} ${EGL-lib})

#===================================

#for inference-gpu comment this block

set(OPTIMIZATION_FLAGS -Ofast -fopenmp-simd -funroll-all-loops -Wall -Wextra)
add_library(${CMAKE_PROJECT_NAME} SHARED inference.c)
target_link_libraries(${CMAKE_PROJECT_NAME} android log -lm -fopenmp -static-openmp)

#===================================

target_compile_options(${PROJECT_NAME} PUBLIC $<$<COMPILE_LANGUAGE:C>:${OPTIMIZATION_FLAGS}>)