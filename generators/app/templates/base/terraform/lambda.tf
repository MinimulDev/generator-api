variable function_name {
  default = "<%= apiName %>-lambda"
}

resource "aws_lambda_permission" "permission" {
  statement_id = "AllowExecutionFromAPIGateway"
  action = "lambda:InvokeFunction"
  function_name = aws_lambda_function.lambda.function_name
  principal = "apigateway.amazonaws.com"
  source_arn = "${aws_api_gateway_rest_api.api.execution_arn}/*/*/*"
}

resource "aws_lambda_function" "lambda" {
  filename = "../server/build/function.zip"
  function_name = var.function_name
  role = aws_iam_role.lambda_role.arn
  handler = "provided"
  runtime = "provided"
  source_code_hash = filebase64sha256("../server/build/function.zip")

  depends_on = [
    aws_iam_role_policy_attachment.lambda_logs,
    aws_cloudwatch_log_group.logging,
  ]
}