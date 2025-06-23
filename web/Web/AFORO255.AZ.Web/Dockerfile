#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["AFORO255.AZ.Web/AFORO255.AZ.Web.csproj", "AFORO255.AZ.Web/"]
RUN dotnet restore "AFORO255.AZ.Web/AFORO255.AZ.Web.csproj"
COPY . .
WORKDIR "/src/AFORO255.AZ.Web"
RUN dotnet build "AFORO255.AZ.Web.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "AFORO255.AZ.Web.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "AFORO255.AZ.Web.dll"]