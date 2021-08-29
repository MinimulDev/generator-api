resource "aws_cloudwatch_log_group" "logging" {
  name = "/aws/lambda/${var.function_name}"
}