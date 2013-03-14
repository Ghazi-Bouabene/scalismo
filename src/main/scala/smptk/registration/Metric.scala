
package smptk.registration

import scala.language.higherKinds
import TransformationSpace.ParameterVector
import smptk.numerics.Integration._
import breeze.linalg.DenseVector
import smptk.image.ContinuousScalarImage
import smptk.image.CoordVector
import smptk.image.DiscreteImageDomain
import smptk.image.ContinuousScalarImage1D
import smptk.image.DiscreteImageDomain1D
import smptk.image.Geometry.CoordVector1D
import smptk.numerics.Integration

trait ImageMetric[CV[A] <: CoordVector[A]] {
  type Repr  = ContinuousScalarImage[CV]

  def apply(img1: Repr, img2: Repr): (DiscreteImageDomain[CV] => Double)

  def takeDerivativeWRTToMovingImage(fixedImage: Repr, movingImage: Repr): ContinuousScalarImage[CV]
}

trait ImageMetric1D extends  ImageMetric[CoordVector1D] {

}

object MeanSquaresMetric1D extends ImageMetric1D {

  def apply(img1: ContinuousScalarImage[CoordVector1D],
    img2: ContinuousScalarImage[CoordVector1D]) = {
    (region : DiscreteImageDomain[CoordVector1D]) => Integration.integrate((img1 - img2).square, region)
  }
  def takeDerivativeWRTToMovingImage(img1: ContinuousScalarImage[CoordVector1D],
    img2: ContinuousScalarImage[CoordVector1D]) = {
    (img1 - img2) * 2f
  }

}

object Metric {
}