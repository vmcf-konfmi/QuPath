import qupath.ext.stardist.StarDist2D

// Specify the model file (you will need to change this!)
def pathModel = "D:\\apps\\QuPath\\stardist\\dsb2018_heavy_augment.pb"

def stardist = StarDist2D.builder(pathModel)
        .threshold(0.5)              // Probability (detection) threshold
        .channels('Green')            // Specify detection channel
        //.preprocess(        // Extra preprocessing steps, applied sequentially
        //      ImageOps.Core.subtract(100),
        //      ImageOps.Core.divide(100)
        //  )
        .normalizePercentiles(1, 99) // Percentile normalization
        .pixelSize(0.15)              // Resolution for detection
        .build()

// Run detection for the selected objects
def imageData = getCurrentImageData()
def pathObjects = getSelectedObjects()
if (pathObjects.isEmpty()) {
    Dialogs.showErrorMessage("StarDist", "Please select a parent object!")
    return
}
stardist.detectObjects(imageData, pathObjects)
println 'Done!'