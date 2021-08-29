terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~> 3.51.0"
    }
  }
}

variable app_name {
  default = "<%= apiName %>"
}

variable app_description {
  default = "<%= apiName %>"
}

variable "region" {
  default = "us-east-1"
}

provider "aws" {
  profile = "default"
  region = var.region
}